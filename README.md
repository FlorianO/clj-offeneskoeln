#`clj-offeneskoeln`

A clojure library for the [Offenes Köln](http://www.offeneskoeln.de) [api](http://offeneskoeln.de/api/).

## Installation

clj-offeneskoeln will soon be available from [Clojars](http://clojars.org/clj-http):
```clojure
[clj-offeneskoeln "0.1.0"]
```

## Usage 

Everything is available in `clj-offeneskoeln.core` namespace.

If you want to use it from the repl:
```clojure
(use 'clj-offeneskoeln.core)
```

If you want to use it in a project:
```clojure
(ns your-fancy.app
    (:require [clj-offeneskoeln :as k]))
```

A request always looks like this:
```clojure
(k/offenes-koeln req-type req-data)
```
where `req-type` is a given map from:
* get-documents
* search-documents
* get-locations
* get-streets
* get-session
* get-terms

and req-data is a map with your values.

### Valid parameter for the request-data

#### get-documents

* `:reference` - single or comma-separated document references 
* `:output` - single or comma-separated output type (attachments, thumbnails, consultations, facets)
* `:sort-order` - asc, desc date asc or date desc 

#### search-documents

* `:query` - A query string. Supports Lucene query syntax
* `:filter-query` - Filter based on a document property. For example "type:Antrag". Lucene query syntax.
  * title
  * term
  * type
  * person
  * attachment
  * session
  * comittee
  * street
* `:sort-order` - 
* `:output` - single or comma-separated output type (attachments, thumbnails, consultations, facets) 
* `:maximum-results` - Number of maximum returned documents
* `:result-offset` - How many documents to skip.
* `:date-filter` - Filter by date.

#### get-locations

#### get-streets

#### get-session

#### get-terms 

## Examples from the api documentation

### Get documents

```clojure
(k/offenes-koeln get-documents {:reference "2961/2011"})
```

```clojure
(k/offenes-koeln get-documents {:reference "2961/2011" :output "attachments"})
```

```clojure
(k/offenes-koeln get-documents {:reference "2961/2011" :output "attachments,thumbnails"})
```

### Search documents

```clojure
(k/offenes-koeln search-documents {:query "Bycicle"})
```

```clojure
(k/offenes-koeln search-documents {:filter-query "type:Antrag"})
```

### Get location data for a street

Get all Details for "Roncalliplatz"
```clojure
(k/offenes-koeln get-locations {:street "Roncalliplatz"})
```

Get the average location for "Marzellenstraße"
```clojure
(k/offenes-koeln get-locations {:street "Marzellenstraße" :output "averages"})
```

Get the nodes for "Aachener Straße"
```clojure
(k/offenes-koeln get-locations {:street "Aachener Straße" :output "nodes"})
```

### Find streets for a given location

Get all streets around 50,959/6,946.
```clojure
(k/offenes-koeln get-street {:latitude "50.959" :longitude "6.946"})
```

### Get and set session data

```clojure
(k/offenes-koeln get-session {})
```

### Get document terms

```clojure
(k/offenes-koeln get-terms {})
```

## Continuous Integration

[![Continuous Integration status](https://secure.travis-ci.org/FlorianO/clj-offeneskoeln.png)](http://travis-ci.org/FlorianO/clj-offeneskoeln)

## License

Copyright © 2013 Florian Over

Distributed under the Eclipse Public License, the same as Clojure.
