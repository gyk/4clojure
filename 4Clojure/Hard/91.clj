(fn [g]
  (letfn [(un [dicts [p q]]
            (let [has? #(or (% p) (% q))]
              (cons (apply clojure.set/union (set [p q]) (filter has? dicts)) (remove has? dicts))))]
    (->> (reduce un #{} g) count (= 1))))
