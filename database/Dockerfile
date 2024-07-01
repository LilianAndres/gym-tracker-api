FROM postgres:16.3-alpine

ENV POSTGRES_USER=postgres
ENV POSTGRES_PASSWORD=postgres
ENV POSTGRES_DB=gymtracker

RUN mkdir -p /docker-entrypoint-initdb.d

COPY scripts/*.sql /docker-entrypoint-initdb.d/

EXPOSE 5432
