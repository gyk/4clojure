(fn [& ss]
  (let [sum-f
          (fn [s]
            (set
              (keep #(if (empty? %) nil (apply + %))
                (reduce 
                  (fn [a b] 
                    (into a (map #(conj % b) a))) #{[]} s))))
        sums (map sum-f ss)]
    (-> (apply clojure.set/intersection sums) empty? not)))
