(fn [& fs]
  (let [[f & fs] (reverse fs)]
    (fn [& xs]
      (reduce #(%2 %) (apply f xs) fs))))
