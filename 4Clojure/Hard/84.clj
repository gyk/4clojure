(fn [ps]
  (let [m (into {} (vec ps))
        from-here
          (fn [x]
            (loop [out []
                   k x]
              (let [v (m k)]
                (if v
                  (recur (conj out [x v]) v)
                  out))))]
    (set (apply concat (map #(from-here %) (keys m))))))
