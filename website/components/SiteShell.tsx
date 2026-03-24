'use client';

import Link from 'next/link';
import {usePathname} from 'next/navigation';
import {useEffect} from 'react';
import {alternateLocalePath, localeFromPathname, routePrefix} from '@/lib/locale';
import {getMessages} from '@/lib/messages';

export default function SiteShell({children}: {children: React.ReactNode}) {
    const pathname = usePathname() || '/';
    const locale = localeFromPathname(pathname);
    const m = getMessages(locale);
    const prefix = routePrefix(locale);
    const alt = alternateLocalePath(pathname);

    useEffect(() => {
        document.documentElement.lang = locale === 'zh' ? 'zh-CN' : 'en';
    }, [locale]);

    return (
        <>
            <header className="border-b bg-white/80 backdrop-blur sticky top-0 z-50">
                <nav
                    className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 h-16 flex items-center justify-between gap-4 flex-wrap">
                    <Link href={prefix || '/'} className="text-xl font-bold text-primary-700">
                        JMock
                    </Link>
                    <div className="flex flex-wrap items-center gap-4 sm:gap-6 text-sm font-medium text-slate-600">
                        <Link href={`${prefix}/docs/getting-started`} className="hover:text-primary-600">
                            {m.nav.docs}
                        </Link>
                        <Link href={`${prefix}/docs/functions`} className="hover:text-primary-600">
                            {m.nav.functions}
                        </Link>
                        <Link href={`${prefix}/playground`} className="hover:text-primary-600">
                            {m.nav.playground}
                        </Link>
                        <a
                            href="https://github.com/xcancloud/JMock"
                            target="_blank"
                            rel="noopener noreferrer"
                            className="hover:text-primary-600"
                        >
                            {m.nav.github}
                        </a>
                        <Link
                            href={alt.href}
                            className="text-slate-400 hover:text-primary-600 border-l border-slate-200 pl-4 sm:pl-6"
                            hrefLang={locale === 'en' ? 'zh-CN' : 'en'}
                        >
                            {alt.label}
                        </Link>
                    </div>
                </nav>
            </header>
            <main>{children}</main>
            <footer className="border-t py-8 mt-16 text-center text-sm text-slate-500">
                <p>
                    JMock &copy; {new Date().getFullYear()} — {m.footer}
                </p>
            </footer>
        </>
    );
}
