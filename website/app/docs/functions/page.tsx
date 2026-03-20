import { loadFunctionSpec, getCategories } from '@/lib/loadFunctions';
import FunctionCatalog from '@/components/FunctionCatalog';

export default function FunctionsPage() {
  const spec = loadFunctionSpec('en');
  const categories = getCategories(spec);

  return (
    <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-12">
      <h1 className="text-3xl font-bold mb-2">Function Reference</h1>
      <p className="text-slate-500 mb-8">
        {spec.functions.length} built-in functions across {categories.length} categories
      </p>
      <FunctionCatalog functions={spec.functions} categories={categories} />
    </div>
  );
}
