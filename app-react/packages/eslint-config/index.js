module.exports = {
  // 필요한 플러그인을 여기에 정의합니다.
  plugins: ["no-relative-import-paths"],
  extends: [
    // ✅ (필수) rushstack 컨피그를 가져옵니다.
    "@rushstack/eslint-config/profile/web-app",
  ],
  rules: {
    // 필요한 커스텀 규칙을 여기에 정의합니다.
    "@typescript-eslint/explicit-function-return-type": "off",
    "@typescript-eslint/naming-convention": [
      "warn",
      // camelCase 변수, PascalCase 변수, UPPER_CASE 변수 허용
      {
        selector: "variable",
        format: ["camelCase", "PascalCase", "UPPER_CASE"],
      },
      // camelCase 함수, PascalCase 함수 허용
      {
        selector: "function",
        format: ["camelCase", "PascalCase"],
      },
      // PascalCase 클래스, interfaces, type aliases, enums 허용
      {
        selector: "typeLike",
        format: ["PascalCase"],
      },
      // interface 앞에 I 사용 불가
      {
        selector: "interface",
        format: ["PascalCase"],
        custom: {
          regex: "^I[A-Z]",
          match: false,
        },
      },
      // typeAlias 앞에 T 사용 불가
      {
        selector: "typeAlias",
        format: ["PascalCase"],
        custom: {
          regex: "^T[A-Z]",
          match: false,
        },
      },
      // typeParameter 앞에 T 사용 불가
      {
        selector: "typeParameter",
        format: ["PascalCase"],
        custom: {
          regex: "^T[A-Z]",
          match: false,
        },
      },
    ],
    // 같은 폴더인 경우를 제외하고 import 경로는 항상 절대 경로를 사용
    "no-relative-import-paths/no-relative-import-paths": [
      "warn",
      { allowSameFolder: true, rootDir: "src", prefix: "@" },
    ],
    // 구조 분해 할당 강제 규칙
    "prefer-destructuring": [
      "error",
      {
        VariableDeclarator: {
          array: false,
          object: true,
        },
        AssignmentExpression: {
          array: false,
          object: false,
        },
      },
    ],
  },
  settings: {
    // 공통으로 넣고 싶은 설정이 있으면 추가합니다.
  },
};
