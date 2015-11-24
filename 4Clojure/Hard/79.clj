(fn [xss]
  (first
    (reduce
      (fn [l h]
        (map min (map + (rest l) h) (map + (butlast l) h)))
      (reverse xss))))
