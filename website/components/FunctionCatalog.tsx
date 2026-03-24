'use client';

import {useState} from 'react';
import type {MockFunctionDef} from '@/lib/types';

interface Props {
    functions: MockFunctionDef[];
    categories: string[];
}

export default function FunctionCatalog({functions, categories}: Props) {
    const [search, setSearch] = useState('');
    const [selectedCategory, setSelectedCategory] = useState<string | null>(null);

    const filtered = functions.filter((fn) => {
        const matchesSearch =
            !search ||
            fn.name.toLowerCase().includes(search.toLowerCase()) ||
            fn.description.toLowerCase().includes(search.toLowerCase());
        const matchesCategory =
            !selectedCategory ||
            fn.categoryId === selectedCategory ||
            fn.tags?.includes(selectedCategory);
        return matchesSearch && matchesCategory;
    });

    return (
        <div>
            {/* Search & Filter */}
            <div className="flex flex-col sm:flex-row gap-4 mb-8">
                <input
                    type="text"
                    placeholder="Search functions..."
                    value={search}
                    onChange={(e) => setSearch(e.target.value)}
                    className="flex-1 px-4 py-2 border rounded-lg text-sm focus:ring-2 focus:ring-primary-500"
                />
                <div className="flex flex-wrap gap-2">
                    <button
                        onClick={() => setSelectedCategory(null)}
                        className={`px-3 py-1 rounded-full text-xs font-medium transition ${
                            !selectedCategory
                                ? 'bg-primary-600 text-white'
                                : 'bg-slate-100 text-slate-600 hover:bg-slate-200'
                        }`}
                    >
                        All
                    </button>
                    {categories.map((cat) => (
                        <button
                            key={cat}
                            onClick={() => setSelectedCategory(cat === selectedCategory ? null : cat)}
                            className={`px-3 py-1 rounded-full text-xs font-medium transition ${
                                selectedCategory === cat
                                    ? 'bg-primary-600 text-white'
                                    : 'bg-slate-100 text-slate-600 hover:bg-slate-200'
                            }`}
                        >
                            {cat}
                        </button>
                    ))}
                </div>
            </div>

            {/* Function Grid */}
            <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-4">
                {filtered.map((fn) => (
                    <a
                        key={fn.name}
                        href={`/docs/functions/${fn.name}`}
                        className="block p-4 bg-white border rounded-lg hover:shadow-md hover:border-primary-200 transition"
                    >
                        <div className="flex items-center justify-between mb-2">
                            <h3 className="font-semibold text-primary-700">@{fn.name}()</h3>
                            {fn.since === '2.0.0' && (
                                <span
                                    className="text-xs bg-green-100 text-green-700 px-2 py-0.5 rounded-full">
                  New
                </span>
                            )}
                        </div>
                        <p className="text-sm text-slate-500 line-clamp-2">{fn.description}</p>
                        <div className="mt-2 flex flex-wrap gap-1">
                            {fn.tags?.map((tag) => (
                                <span key={tag}
                                      className="text-xs bg-slate-100 text-slate-500 px-2 py-0.5 rounded">
                  {tag}
                </span>
                            ))}
                        </div>
                    </a>
                ))}
            </div>

            {filtered.length === 0 && (
                <p className="text-center text-slate-400 py-12">No functions found matching your
                    criteria.</p>
            )}
        </div>
    );
}
