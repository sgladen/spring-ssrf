# README

This repository is part of my master thesis.
I created a small example of an SSRF-vulnerable web application, which I needed for the proof of concept.

The project was generated with [start.spring.io](https://start.spring.io/).

The specifications project is [spring-ssrf-specification](https://github.com/sgladen/spring-ssrf-specification).

## Sources

All sources are located in ``info.gladen.springssrf.SpringssrfApplication``.

A list of current sources:

- ``intraVulnerability``
- ``interVulnerability``
- ``imageVulnerability``

### Sanitized

The source ``sanitize`` is sanitized by the sanitizer located in ``info.gladen.springssrf.SSRFSanitizers``.

## Sinks

Most sinks are located in ``info.gladen.springssrf.SSRFVulnerabilities``.

A list of current sinks:

- ``HTTPRequest`` / ``HTTPClient``
- ``ImageIO.read``

