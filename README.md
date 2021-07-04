# Title

## Overview

### Tech stack

## Running tests

## Driver Manager & Supported Browsers

## POM Profile & Dependencies

## Test Reports

## Project Structure

## Page Object Model & Locators

NB Opens separate browser per test so not the fastest way to run a test suite but Cucumber has no BeforeAll and AfterAll hooks so can't open browser at start of test suite and close it at the end. There are ways round using JUnit annotations and mvn phases but nothing that works consistently when tests may be run via IDE as well as from the command line (or CI pipelines)

## CI Pipeline