CREATE SCHEMA IF NOT EXISTS public;
--
DROP TABLE IF EXISTS voter_ref;
DROP TABLE IF EXISTS candidate_ref;
DROP TABLE IF EXISTS voter;
DROP TABLE IF EXISTS candidate;
DROP TABLE IF EXISTS election;
--
CREATE TABLE voter (
    id UUID NOT NULL DEFAULT RANDOM_UUID(),
    name VARCHAR(255) NOT NULL,
    CONSTRAINT voter_pk PRIMARY KEY (id)
);
--
CREATE TABLE candidate (
    id UUID NOT NULL DEFAULT RANDOM_UUID(),
    name VARCHAR(255) NOT NULL,
    CONSTRAINT candidate_pk PRIMARY KEY (id)
);
CREATE TABLE voter_ref (
    voter UUID NOT NULL,
    candidate UUID NOT NULL,
    candidate_key BIGINT NOT NULL
);
--
CREATE TABLE election (
    id UUID NOT NULL DEFAULT RANDOM_UUID(),
    name VARCHAR(255) NOT NULL,
    CONSTRAINT election_pk PRIMARY KEY (id)
);
CREATE TABLE candidate_ref (
    candidate UUID NOT NULL,
    election UUID NOT NULL,
    election_key BIGINT NOT NULL
);
--
