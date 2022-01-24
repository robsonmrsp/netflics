# final build stage
FROM postgres:11-alpine

ENV POSTGRES_DB=db_netflics
ENV POSTGRES_USER=postgres
ENV POSTGRES_PASSWORD=123456a

