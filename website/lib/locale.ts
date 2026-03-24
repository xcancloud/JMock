export type UiLocale = 'en' | 'zh';

/** JSON spec files use en / zh_CN */
export function dataLocale(ui: UiLocale): 'en' | 'zh_CN' {
    return ui === 'zh' ? 'zh_CN' : 'en';
}

/** Prefix for internal routes (/zh/... for Chinese UI) */
export function routePrefix(ui: UiLocale): string {
    return ui === 'zh' ? '/zh' : '';
}

/** Resolve UI locale from pathname (basePath already stripped by Next.js router). */
export function localeFromPathname(pathname: string): UiLocale {
    const seg = pathname.split('/').filter(Boolean)[0];
    return seg === 'zh' ? 'zh' : 'en';
}

/** Path to the same page in the other UI language (for nav toggle). */
export function alternateLocalePath(pathname: string): {href: string; label: string} {
    const loc = localeFromPathname(pathname);
    if (loc === 'en') {
        return {
            href: pathname === '/' ? '/zh' : `/zh${pathname}`,
            label: '中文',
        };
    }
    if (pathname === '/zh') {
        return {href: '/', label: 'English'};
    }
    const rest = pathname.startsWith('/zh/') ? pathname.slice(3) : pathname;
    return {href: rest || '/', label: 'English'};
}
