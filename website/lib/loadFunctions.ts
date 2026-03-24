import fs from 'fs';
import path from 'path';
import type {Category, ConstructorDef, FunctionSpec, MockFunctionDef, ParameterDef} from './types';

const SPEC_DIR = path.join(process.cwd(), '..', 'docs');

/** Slug used in URLs and findFunction, e.g. "@String()" → "String". */
function slugFromExportName(exportName: string): string {
    let s = exportName.trim();
    if (s.startsWith('@')) s = s.slice(1);
    if (s.endsWith('()')) s = s.slice(0, -2);
    return s || exportName;
}

function asRecord(v: unknown): Record<string, unknown> | null {
    return v !== null && typeof v === 'object' && !Array.isArray(v) ? (v as Record<string, unknown>) : null;
}

function normalizeParameter(j: Record<string, unknown>): ParameterDef {
    return {
        name: String(j.name ?? ''),
        type: String(j.type ?? ''),
        required: Boolean(j.required),
        defaultValue: String(j.defaultValue ?? ''),
        description: String(j.description ?? ''),
    };
}

function normalizeConstructor(j: Record<string, unknown>): ConstructorDef {
    const exampleOutput = j.exampleOutput ?? j.exampleValues;
    const out: string[] = Array.isArray(exampleOutput)
        ? exampleOutput.map((x) => String(x))
        : [];
    const sig = j.signature ?? j.instance;
    return {
        signature: sig != null ? String(sig) : '',
        description: String(j.description ?? ''),
        parameters: Array.isArray(j.parameters)
            ? (j.parameters as unknown[]).map((p) => {
                  const r = asRecord(p);
                  return r ? String(r.name ?? '') : '';
              })
            : [],
        example: String(j.example ?? ''),
        exampleOutput: out,
    };
}

/**
 * Map Java MockFunctionDocParser JSON (root array or v2 wrapper) to FunctionSpec.
 */
function normalizeFunctionSpec(parsed: unknown): FunctionSpec {
    const now = new Date().toISOString();
    let version = '2.0.0';
    let generatedAt = now;
    let categories: Category[] = [];
    let functionsRaw: unknown[] = [];

    if (Array.isArray(parsed)) {
        functionsRaw = parsed;
    } else {
        const o = asRecord(parsed);
        if (o && Array.isArray(o.functions)) {
            functionsRaw = o.functions as unknown[];
            if (typeof o.version === 'string') version = o.version;
            if (typeof o.generatedAt === 'string') generatedAt = o.generatedAt;
            if (Array.isArray(o.categories)) categories = o.categories as Category[];
        }
    }

    const functions: MockFunctionDef[] = functionsRaw.map((item) => {
        const j = asRecord(item) ?? {};
        const nameRaw = String(j.name ?? '');
        const name = slugFromExportName(nameRaw);
        const tags = Array.isArray(j.tags) ? (j.tags as unknown[]).map(String) : [];
        const categoryId =
            typeof j.categoryId === 'string'
                ? j.categoryId
                : tags[0] ?? 'uncategorized';
        const clazz = String(j.class ?? j.clazz ?? '');
        const paramsIn = Array.isArray(j.parameters) ? j.parameters : [];
        const parameters: ParameterDef[] = paramsIn.map((p) => {
            const r = asRecord(p);
            return r ? normalizeParameter(r) : normalizeParameter({});
        });
        const ctorsIn = Array.isArray(j.constructors) ? j.constructors : [];
        const constructors: ConstructorDef[] = ctorsIn
            .map((c) => {
                const r = asRecord(c);
                return r ? normalizeConstructor(r) : null;
            })
            .filter((c): c is ConstructorDef => c !== null);

        return {
            name,
            class: clazz,
            description: String(j.description ?? ''),
            categoryId,
            since: String(j.since ?? '2.0.0'),
            deprecated: Boolean(j.deprecated),
            tags,
            returnType: String(j.returnType ?? ''),
            parameters,
            constructors,
        };
    });

    return {version, generatedAt, categories, functions};
}

/**
 * Load function spec JSON for a given locale.
 * Falls back to English if the locale-specific file doesn't exist yet.
 */
export function loadFunctionSpec(locale: string = 'en'): FunctionSpec {
    const suffix = locale === 'zh_CN' ? 'zh_CN' : 'en';
    const filePath = path.join(SPEC_DIR, `JMockFunction-${suffix}.json`);

    if (!fs.existsSync(filePath)) {
        // Return empty spec if file doesn't exist yet
        return {
            version: '2.0.0',
            generatedAt: new Date().toISOString(),
            categories: [],
            functions: [],
        };
    }

    const raw = fs.readFileSync(filePath, 'utf-8');
    return normalizeFunctionSpec(JSON.parse(raw) as unknown);
}

/**
 * Get all unique category names from the spec.
 */
function functionsList(spec: FunctionSpec): MockFunctionDef[] {
    return Array.isArray(spec.functions) ? spec.functions : [];
}

export function getCategories(spec: FunctionSpec): string[] {
    const cats = new Set<string>();
    for (const fn of functionsList(spec)) {
        if (fn.categoryId) cats.add(fn.categoryId);
        fn.tags?.forEach((t) => cats.add(t));
    }
    return Array.from(cats).sort();
}

/**
 * Get all function names for static path generation.
 */
export function getAllFunctionNames(spec: FunctionSpec): string[] {
    return functionsList(spec).map((f) => f.name);
}

/**
 * Find a function by name (case-insensitive).
 */
export function findFunction(
    spec: FunctionSpec,
    name: string,
): MockFunctionDef | undefined {
    return functionsList(spec).find(
        (f) => f.name.toLowerCase() === name.toLowerCase(),
    );
}
