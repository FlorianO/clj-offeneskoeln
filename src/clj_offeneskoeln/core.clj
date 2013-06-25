(ns clj-offeneskoeln.core
  (:require [clj-http.client :as client]))

(def default-header {:as :json})
(def base-url "http://offeneskoeln.de")

(def get-documents
  {:type :http/get
   :resource (str base-url "/api/documents")
   :parameter {:reference :reference ;; kann ein einzelner String oder viele sein
               :output :output-types ;; attachments, thumbnails, consultations, facets (also several , separated)
               :sort :sort-order}})  

(def search-documents
  {:type :http/get
   :resource (str base-url "/api/documents")
   :parameter {:output :output-types
               :q :query
               :fq :filter-query ;; title, term, type, person, attachment, session, committee, street
               :docs :maximum-results ;; make pagination?
               :start :result-offset ;; make pagination?
               :sort :sort-order 
               :date :date-filter}})

(def get-locations 
  {:type :http/get
   :resource (str base-url "/api/locations")
   :parameter {:street :street
               :output :output-types}})

(def get-streets 
  {:type :http/get
   :resource (str base-url "/api/streets")
   :parameter {:lat :latitude
               :lon :longitude
               :radius :radius}})

(def get-session 
  {:type :http/get
   :resource (str base-url "/api/session")
   :parameter {:lat :latitude
               :lon :longitude
               :location-entry :location}})

(def get-terms
  {:type :http/get
   :resource (str base-url "/api/terms")
   :parameter {:prefix :begins-with}})

;; TODO make simpler
(defn generate-parameters
  [req-map req-data]
  (let [req default-header] 
    (-> default-header 
        (merge {:query-params  (into {} (remove (comp nil? val) (into {} (map #(vector (key %) (get req-data (val %))) (:parameter req-map)))))}))))

(defn offenes-koeln
  [req-map req-data]
  (case (:type req-map)
    :http/get (client/get (:resource req-map) (generate-parameters req-map req-data))
    :else (println "http method not supported")))
