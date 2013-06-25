#`clj-offeneskoeln`

A clojure library for the [Offenes Köln](www.offeneskoeln.de) api.

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

```clojure
(k/offenes-koeln get-locations {})
```

### Find streets for a given location

```clojure
(k/offenes-koeln get-street {})
```

### Get and set session data

```clojure
(k/offenes-koeln get-session {})
```

### Get document terms

```clojure
(k/offenes-koeln get-terms {})
```

## License

Copyright © 2013 Florian Over

Distributed under the Eclipse Public License, the same as Clojure.
