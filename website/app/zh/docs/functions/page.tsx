import FunctionCatalog from '@/components/FunctionCatalog';
import {getCategories, loadFunctionSpec} from '@/lib/loadFunctions';
import {dataLocale, routePrefix} from '@/lib/locale';
import {functionsPageSubtitle, getMessages} from '@/lib/messages';

export default function ZhFunctionsPage() {
    const spec = loadFunctionSpec(dataLocale('zh'));
    const categories = getCategories(spec);
    const m = getMessages('zh');
    const fnLen = Array.isArray(spec.functions) ? spec.functions.length : 0;

    return (
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-12">
            <h1 className="text-3xl font-bold mb-2">{m.functionsPage.title}</h1>
            <p className="text-slate-500 mb-8">
                {functionsPageSubtitle('zh', fnLen, categories.length)}
            </p>
            <FunctionCatalog
                functions={Array.isArray(spec.functions) ? spec.functions : []}
                categories={categories}
                localePrefix={routePrefix('zh')}
                messages={m.catalog}
            />
        </div>
    );
}
