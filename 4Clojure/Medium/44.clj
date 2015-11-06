#(let [k (mod % (count %2))]
  (concat (drop k %2) (take k %2)))
