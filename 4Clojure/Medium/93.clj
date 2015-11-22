(fn pf [xs]
  (if (and (coll? xs) (coll? (first xs)))
    (if (= 1 (count xs))
      (recur (first xs))
      (apply concat (map pf xs)))
    (list xs)))
