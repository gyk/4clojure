#(loop [d {} xs %]
  (let [k (first xs)
       [v xs] (split-with number? (rest xs))]
    (if (nil? k)
      d
      (recur (assoc d k v) xs))))
