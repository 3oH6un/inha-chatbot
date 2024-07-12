import { defineConfig } from "vite";
import react from "@vitejs/plugin-react-swc";
import path from "path";

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [react()],
  resolve: {
    alias: {
      "@": path.resolve(__dirname, "src"),
    },
  },
  server: {
    proxy: {
      "/itc-proxy": {
        target: "https://ipsi.inhatc.ac.kr",
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/itc-proxy/, ""),
      },
      "/app-springboot": {
        target: "http://springboot:8080",
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/app-springboot/, ""),
      },
    },
  },
  build: {
    outDir: path.resolve(__dirname, "dist"),
  },
});
