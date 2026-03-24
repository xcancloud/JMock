'use client';

import {useState} from 'react';
import type {Messages} from '@/lib/messages';

interface Props {
    messages: Messages;
}

export default function PlaygroundClient({messages: m}: Props) {
    const [template, setTemplate] = useState(`{
  "name": "@Name()",
  "email": "@Email()",
  "age": "@Integer(18,65)",
  "active": "@Bool()"
}`);
    const [output, setOutput] = useState('');

    const handleGenerate = () => {
        let result = template;
        result = result.replace(/@Name\(\)/g, () => pickRandom(['Alice', 'Bob', 'Charlie', 'Diana', 'Eve']));
        result = result.replace(/@Email\(\)/g, () =>
            pickRandom(['alice@example.com', 'bob@test.org', 'charlie@mail.com']),
        );
        result = result.replace(/@Integer\((\d+),(\d+)\)/g, (_, min, max) =>
            String(Math.floor(Math.random() * (Number(max) - Number(min) + 1)) + Number(min)),
        );
        result = result.replace(/@Bool\(\)/g, () => (Math.random() > 0.5 ? 'true' : 'false'));
        result = result.replace(/@String\(\)/g, () => randomString(6));
        result = result.replace(/@String\((\d+)\)/g, (_, len) => randomString(Number(len)));
        setOutput(result);
    };

    return (
        <div className="max-w-6xl mx-auto px-4 sm:px-6 lg:px-8 py-10 sm:py-12">
            <h1 className="text-3xl sm:text-4xl font-extrabold tracking-tight text-slate-900 mb-2">
                {m.playground.title}
            </h1>
            <p className="text-slate-600 text-base sm:text-lg max-w-3xl leading-relaxed">{m.playground.subtitle}</p>

            <aside
                className="mt-6 flex gap-3 rounded-xl border border-amber-200/90 bg-amber-50/90 px-4 py-3.5 text-sm text-amber-950 shadow-sm"
                role="note"
            >
                <span className="select-none text-lg leading-none shrink-0" aria-hidden>
                    ℹ️
                </span>
                <p className="leading-relaxed text-amber-950/90">{m.playground.javaLibraryHint}</p>
            </aside>

            <div className="mt-10 grid grid-cols-1 lg:grid-cols-2 gap-6 lg:gap-8">
                <div className="flex flex-col">
                    <label className="block text-sm font-semibold text-slate-800 mb-2">{m.playground.template}</label>
                    <textarea
                        className="w-full min-h-[20rem] flex-1 font-mono text-sm p-4 border border-slate-200 rounded-xl bg-white shadow-sm focus:ring-2 focus:ring-primary-500 focus:border-primary-500 transition-shadow"
                        value={template}
                        onChange={(e) => setTemplate(e.target.value)}
                        spellCheck={false}
                    />
                    <button
                        type="button"
                        onClick={handleGenerate}
                        className="mt-4 w-full sm:w-auto px-8 py-2.5 bg-primary-600 text-white rounded-xl font-semibold hover:bg-primary-700 shadow-md shadow-primary-600/20 transition"
                    >
                        {m.playground.generate}
                    </button>
                </div>
                <div className="flex flex-col">
                    <label className="block text-sm font-semibold text-slate-800 mb-2">{m.playground.output}</label>
                    <pre
                        className="w-full min-h-[20rem] flex-1 font-mono text-sm p-4 border border-slate-200 rounded-xl bg-slate-50/90 overflow-auto whitespace-pre-wrap text-slate-800 shadow-inner">
                        {output || m.playground.outputPlaceholder}
                    </pre>
                </div>
            </div>
        </div>
    );
}

function pickRandom<T>(arr: T[]): T {
    return arr[Math.floor(Math.random() * arr.length)];
}

function randomString(len: number): string {
    const chars = 'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789';
    let result = '';
    for (let i = 0; i < len; i++) {
        result += chars.charAt(Math.floor(Math.random() * chars.length));
    }
    return result;
}
