import HomeContent from '@/components/HomeContent';
import {getCategories, loadFunctionSpec} from '@/lib/loadFunctions';
import {dataLocale} from '@/lib/locale';
import {getMessages} from '@/lib/messages';

export default function ZhHomePage() {
    const spec = loadFunctionSpec(dataLocale('zh'));
    const categories = getCategories(spec);

    return (
        <HomeContent
            spec={spec}
            categories={categories}
            messages={getMessages('zh')}
            locale="zh"
        />
    );
}
