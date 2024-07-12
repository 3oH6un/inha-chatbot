/**
 * 날짜 객체를 보장합니다.
 * @param date 날짜 또는 문자열
 * @returns 날짜 객체
 */
export const ensureDate = (date: Date | string): Date => {
  return typeof date === "string" ? new Date(date) : date;
};

/**
 * 날짜를 형식화합니다.
 * @param date 날짜 또는 문자열
 * @returns 형식화된 날짜 문자열
 */
export const formatDate = (date: Date | string) => {
  const validDate = ensureDate(date);
  return validDate.toLocaleDateString();
};

/**
 * 시간을 형식화합니다.
 * @param date 날짜 또는 문자열
 * @returns 형식화된 시간 문자열
 */
export const formatTime = (date: Date | string) => {
  const validDate = ensureDate(date);
  return validDate.toLocaleTimeString([], { hour: "2-digit", minute: "2-digit" });
};
