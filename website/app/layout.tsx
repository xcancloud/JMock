import type {Metadata} from 'next';
import SiteShell from '@/components/SiteShell';
import './globals.css';

export const metadata: Metadata = {
    title: 'JMock — High-Performance Mock Data Generator',
    description:
        'Generate realistic mock data at 2M+ records/sec with 130+ built-in functions. Pluggable, i18n-ready, and fully customizable.',
};

export default function RootLayout({children}: {children: React.ReactNode}) {
    return (
        <html lang="en" suppressHydrationWarning>
            <body className="min-h-screen">
                <SiteShell>{children}</SiteShell>
            </body>
        </html>
    );
}
