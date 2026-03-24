import type {Metadata} from 'next';
import './globals.css';

export const metadata: Metadata = {
    title: 'JMock — High-Performance Mock Data Generator',
    description:
        'Generate realistic mock data at 2M+ records/sec with 130+ built-in functions. Pluggable, i18n-ready, and fully customizable.',
};

export default function RootLayout({
                                       children,
                                   }: {
    children: React.ReactNode;
}) {
    return (
        <html lang="en">
        <body className="min-h-screen">
        <header className="border-b bg-white/80 backdrop-blur sticky top-0 z-50">
            <nav
                className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 h-16 flex items-center justify-between">
                <a href="/" className="text-xl font-bold text-primary-700">
                    JMock
                </a>
                <div className="flex gap-6 text-sm font-medium text-slate-600">
                    <a href="/docs/getting-started" className="hover:text-primary-600">
                        Docs
                    </a>
                    <a href="/docs/functions" className="hover:text-primary-600">
                        Functions
                    </a>
                    <a href="/playground" className="hover:text-primary-600">
                        Playground
                    </a>
                    <a
                        href="https://github.com/xcan-cloud/JMock"
                        target="_blank"
                        rel="noopener noreferrer"
                        className="hover:text-primary-600"
                    >
                        GitHub
                    </a>
                </div>
            </nav>
        </header>
        <main>{children}</main>
        <footer className="border-t py-8 mt-16 text-center text-sm text-slate-500">
            <p>JMock &copy; {new Date().getFullYear()} — Open Source Mock Data Framework</p>
        </footer>
        </body>
        </html>
    );
}
