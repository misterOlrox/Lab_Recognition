println "Program started"
dataPath = "../resources/mushrooms.txt"

println "Parsing file \"${dataPath}\""
def X = MushroomDataParser.parse(dataPath)

def X_0 = X as ArrayList

def recognitionAlgorithm = new RecognitionAlgorithm()
recognitionAlgorithm.start(X_0)