(fn [xs]
  (let [from-here
        (fn [from-ind]
          (loop [i from-ind]
            (if (and (< (inc i) (count xs))
                     (< (xs i) (xs (inc i))))
              (recur (inc i))
              i)))]
    (loop [best-so-far 0
           best-from-to [0 -1]
           curr 0]
      (if (< curr (count xs))
        (let [ends-here (from-here curr)
              len (- ends-here curr -1)]
          (if (> len best-so-far)
            (recur len [curr ends-here] (inc ends-here))
            (recur best-so-far best-from-to (inc ends-here))))
        (let [out (subvec xs (first best-from-to) (inc (second best-from-to)))]
          (if (> (count out) 1)
            out
            []))))))
