(fn [f v0 & vs]
  (reduce
    (fn [so-far m]
      (reduce
        (fn [now [k v]]
          (assoc now k (if (now k) (f (now k) v) v)))
        so-far m))
    v0
    vs))
