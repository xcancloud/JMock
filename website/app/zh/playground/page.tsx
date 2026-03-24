import PlaygroundClient from '@/components/PlaygroundClient';
import {getMessages} from '@/lib/messages';

export default function ZhPlaygroundPage() {
    return <PlaygroundClient messages={getMessages('zh')}/>;
}
