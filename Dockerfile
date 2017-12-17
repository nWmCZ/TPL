FROM openjdk:jre-alpine

ARG BUILD_DATE

ARG VCS_REF

LABEL   org.label-schema.build-date=$BUILD_DATE \
        org.label-schema.vcs-url="https://github.com/nWmCZ/TPL.git" \
        org.label-schema.vcs-ref=$VCS_REF \
        org.label-schema.schema-version="1.0.0-rc.1"

ENV TIMEOUT=0

COPY . /

ENTRYPOINT ["sh", "-c", "sleep ${TIMEOUT} && java -jar ./build/libs/TPL-0.0.1-SNAPSHOT.jar"]
