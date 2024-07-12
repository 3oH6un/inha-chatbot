import daisyui from "daisyui";

/** @type {import('tailwindcss').Config} */
export default {
  content: [
    // "./indexa.html",
    "./src/**/*.{js,ts,jsx,tsx}",
  ],
  theme: {
    extend: {},
  },
  plugins: [
    daisyui,
  ],
}

