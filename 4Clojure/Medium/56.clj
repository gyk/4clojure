#((reduce (fn [[a s] x] (if (s x) [a s] [(conj a x) (conj s x)])) [[], #{}] %) 0)
