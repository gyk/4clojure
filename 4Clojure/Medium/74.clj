(fn [s]
  (->> (re-seq #"\d+" s)
       (filter (comp #(= % (Math/ceil %)) #(Math/sqrt %) read-string))
       (clojure.string/join ",")))
