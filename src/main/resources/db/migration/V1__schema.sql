--CREATE EXTENSION IF NOT EXISTS "uuid-ossp"; -- required by uuid_generate_v1
CREATE SCHEMA IF NOT EXISTS public;
--
drop table if exists voter_ref;
drop table if exists candidate_ref;
drop table if exists voter;
drop table if exists candidate;
drop table if exists election;
--
create table voter (
    --id UUID NOT NULL DEFAULT uuid_generate_v1(), -- see line: 1
    id UUID NOT NULL DEFAULT RANDOM_UUID(),
    name varchar(255) not null,
    CONSTRAINT voter_pk PRIMARY KEY ( id )
);
--
create table candidate (
    id UUID NOT NULL DEFAULT RANDOM_UUID(),
    name varchar(255) not null,
    CONSTRAINT candidate_pk PRIMARY KEY ( id )
);
create table voter_ref (
    voter UUID NOT NULL, -- references voter(id),
    candidate UUID NOT NULL, -- references candidate(id),
    --candidate_key UUID NOT NULL
    candidate_key INTEGER NOT NULL
);
--
create table election (
    id UUID NOT NULL DEFAULT RANDOM_UUID(),
    name varchar(255) not null,
    CONSTRAINT election_pk PRIMARY KEY ( id )
);
create table candidate_ref (
    candidate UUID NOT NULL, -- references candidate(id),
    election UUID NOT NULL, -- references election(id),
    --election_key UUID NOT NULL
    election_key INTEGER NOT NULL
);
--
