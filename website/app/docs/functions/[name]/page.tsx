import { loadFunctionSpec, findFunction, getAllFunctionNames } from '@/lib/loadFunctions';
import FunctionDetail from '@/components/FunctionDetail';
import { notFound } from 'next/navigation';

interface Props {
  params: { name: string };
}

export function generateStaticParams() {
  const spec = loadFunctionSpec('en');
  return getAllFunctionNames(spec).map((name) => ({ name }));
}

export default function FunctionDetailPage({ params }: Props) {
  const spec = loadFunctionSpec('en');
  const fn = findFunction(spec, params.name);

  if (!fn) {
    notFound();
  }

  return (
    <div className="max-w-4xl mx-auto px-4 sm:px-6 lg:px-8 py-12">
      <FunctionDetail fn={fn} />
    </div>
  );
}
