// ✅ 앞서 정의한 patch 파일을 불러옵니다.
// 이렇게 하면 ESLint 플러그인들을 프로젝트에서 일일이 설치할 필요가 없어집니다.
require("@unghoe/eslint-config/patch");

module.exports = {
  env: { browser: true, es2020: true },
  extends: [
    "@unghoe/eslint-config", // 공통 ESLint 컨피그 불러오기
    "@unghoe/eslint-config/mixins/react", // React용 ESLint 컨피그 불러오기
  ],
  settings: {
    react: {
      // 현재 React 버전을 명시합니다.
      // 명시하지 않을 경우(기본값 'detect') React 라이브러리 전체를 불러오므로
      // 린트 과정에서 속도가 느려질 수 있습니다.
      // 예: '16.9', '17.0', '18.0' 등
      version: "18.3.1",
    }
  },
  // Rush Stack은 @typescript-eslint 플러그인을 내장하고 있으므로
  // 타입스크립트 파서에 대한 설정이 필요합니다.
  parserOptions: {
    project: ["./tsconfig.json", "./tsconfig.app.json", "./tsconfig.node.json"],
    tsconfigRootDir: __dirname,
  },
};