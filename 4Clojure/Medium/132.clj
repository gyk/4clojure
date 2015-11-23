(fn [p v xs]
  (if (empty? xs)
    ()
    (cons (first xs)
      (apply concat (map #(let [snd (second %)] (if (apply p %) [v snd] [snd])) (partition 2 1 xs))))))
