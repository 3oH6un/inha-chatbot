FROM node:20.15.1-alpine as build

WORKDIR /app-react

RUN npm install -g pnpm

COPY pnpm-lock.yaml ./
COPY package.json ./
RUN pnpm install

COPY . .
RUN pnpm run build

FROM nginx:alpine

COPY --from=build /app-react/dist /usr/share/nginx/html

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]
