import fs from 'fs';
import path from 'path';
import type { FunctionSpec, MockFunctionDef } from './types';

const SPEC_DIR = path.join(process.cwd(), '..', 'docs');

/**
 * Load function spec JSON for a given locale.
 * Falls back to English if the locale-specific file doesn't exist.
 */
export function loadFunctionSpec(locale: string = 'en'): FunctionSpec {
  const suffix = locale === 'zh' ? 'zh' : 'en';
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
  return JSON.parse(raw) as FunctionSpec;
}

/**
 * Get all unique category names from the spec.
 */
export function getCategories(spec: FunctionSpec): string[] {
  const cats = new Set<string>();
  for (const fn of spec.functions) {
    if (fn.categoryId) cats.add(fn.categoryId);
    fn.tags?.forEach((t) => cats.add(t));
  }
  return Array.from(cats).sort();
}

/**
 * Get all function names for static path generation.
 */
export function getAllFunctionNames(spec: FunctionSpec): string[] {
  return spec.functions.map((f) => f.name);
}

/**
 * Find a function by name (case-insensitive).
 */
export function findFunction(
  spec: FunctionSpec,
  name: string,
): MockFunctionDef | undefined {
  return spec.functions.find(
    (f) => f.name.toLowerCase() === name.toLowerCase(),
  );
}
