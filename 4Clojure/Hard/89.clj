(fn [es]
  (let [deg (->>
              es
              (remove #(= (% 0) (% 1)))
              (apply concat)
              frequencies
              vals
              (group-by odd?))]
    (and (not (empty? deg))
         (->> true deg count #{0 2} nil? not))))
