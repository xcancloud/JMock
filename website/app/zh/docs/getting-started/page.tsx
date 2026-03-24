import GettingStartedContent from '@/components/GettingStartedContent';
import {getMessages} from '@/lib/messages';

export default function ZhGettingStartedPage() {
    return <GettingStartedContent messages={getMessages('zh')}/>;
}
