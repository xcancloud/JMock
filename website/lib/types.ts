/**
 * Type definitions for JMock Function Spec v2 JSON format.
 */

export interface FunctionSpec {
    version: string;
    generatedAt: string;
    categories: Category[];
    functions: MockFunctionDef[];
}

export interface Category {
    id: string;
    name: string;
    icon?: string;
    order: number;
}

export interface MockFunctionDef {
    name: string;
    class: string;
    description: string;
    categoryId: string;
    since: string;
    deprecated: boolean;
    tags: string[];
    returnType: string;
    parameters: ParameterDef[];
    constructors: ConstructorDef[];
}

export interface ParameterDef {
    name: string;
    type: string;
    required: boolean;
    defaultValue: string;
    description: string;
}

export interface ConstructorDef {
    signature: string;
    description: string;
    parameters: string[];
    example: string;
    exampleOutput: string[];
}
