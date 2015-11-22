(fn [s] (apply str (map #(%1 %2) (cycle [identity #(.toUpperCase %)]) (re-seq #"(?<=-)\w|\w+" s))))
