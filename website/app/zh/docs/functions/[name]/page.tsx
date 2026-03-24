import FunctionDetail from '@/components/FunctionDetail';
import {findFunction, getAllFunctionNames, loadFunctionSpec} from '@/lib/loadFunctions';
import {dataLocale, routePrefix} from '@/lib/locale';
import {getMessages} from '@/lib/messages';
import {notFound} from 'next/navigation';

interface Props {
    params: {name: string};
}

export function generateStaticParams() {
    const spec = loadFunctionSpec(dataLocale('zh'));
    return getAllFunctionNames(spec).map((name) => ({name}));
}

export default function ZhFunctionDetailPage({params}: Props) {
    const spec = loadFunctionSpec(dataLocale('zh'));
    const fn = findFunction(spec, params.name);
    const m = getMessages('zh');

    if (!fn) {
        notFound();
    }

    return (
        <div className="max-w-4xl mx-auto px-4 sm:px-6 lg:px-8 py-12">
            <FunctionDetail fn={fn} localePrefix={routePrefix('zh')} labels={m.detail}/>
        </div>
    );
}
