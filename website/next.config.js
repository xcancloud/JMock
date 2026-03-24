/**
 * Static export for GitHub Pages (or any static host).
 * - Project site (https://org.github.io/REPO/): build with
 *   NEXT_PUBLIC_BASE_PATH=/REPO (must match the repo name segment).
 * - User/org root site or custom domain: leave NEXT_PUBLIC_BASE_PATH unset.
 * - public/.nojekyll prevents GitHub Pages from ignoring the _next folder.
 */
/** @type {import('next').NextConfig} */
const basePath = process.env.NEXT_PUBLIC_BASE_PATH || '';

const nextConfig = {
    output: 'export',
    images: {unoptimized: true},
    basePath,
};

module.exports = nextConfig;
