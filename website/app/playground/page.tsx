'use client';

import {useState} from 'react';

export default function PlaygroundPage() {
    const [template, setTemplate] = useState(`{
  "name": "@Name()",
  "email": "@Email()",
  "age": "@Integer(18,65)",
  "active": "@Bool()"
}`);
    const [output, setOutput] = useState('');

    const handleGenerate = () => {
        // Client-side mock: parse @Function() tokens and replace with example values
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
        <div className="max-w-6xl mx-auto px-4 sm:px-6 lg:px-8 py-12">
            <h1 className="text-3xl font-bold mb-2">Playground</h1>
            <p className="text-slate-500 mb-8">Enter a JMock template and generate mock data
                instantly.</p>

            <div className="grid grid-cols-1 lg:grid-cols-2 gap-6">
                <div>
                    <label
                        className="block text-sm font-medium text-slate-700 mb-2">Template</label>
                    <textarea
                        className="w-full h-80 font-mono text-sm p-4 border rounded-lg bg-white focus:ring-2 focus:ring-primary-500 focus:border-primary-500"
                        value={template}
                        onChange={(e) => setTemplate(e.target.value)}
                    />
                    <button
                        onClick={handleGenerate}
                        className="mt-4 px-6 py-2 bg-primary-600 text-white rounded-lg font-medium hover:bg-primary-700 transition"
                    >
                        Generate
                    </button>
                </div>
                <div>
                    <label className="block text-sm font-medium text-slate-700 mb-2">Output</label>
                    <pre
                        className="w-full h-80 font-mono text-sm p-4 border rounded-lg bg-slate-50 overflow-auto whitespace-pre-wrap">
            {output || 'Click "Generate" to see output...'}
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
