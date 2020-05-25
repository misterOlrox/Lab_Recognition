class RecognitionAlgorithm {
    List<Mushroom> X_0
    double[][] a

    void start(List<Mushroom> data) {
        X_0 = new ArrayList<>(data)
        Collections.shuffle(X_0)

        def split = X_0.split { it.edible }
        def X_0_edible = split[0] as List<Mushroom>
        def X_0_poison = split[1] as List<Mushroom>
        def X_0_edible_training = X_0_edible.subList(0, 1000)
        def X_0_poison_training = X_0_poison.subList(0, 1000)
        def X_0_edible_control = X_0_edible.subList(1000, 2000)
        def X_0_poison_control = X_0_poison.subList(1000, 2000)

        def trainingSample = X_0_edible_training + X_0_poison_training
        def controlSample = X_0_edible_control + X_0_poison_control

        calculationOfAlgorithmParameters(X_0_edible_training, X_0_poison_training)
        println()

        double[][] s = new double[controlSample.size()][trainingSample.size()]
        for (int i = 0; i < controlSample.size(); i++) {
            Mushroom x = controlSample[i]
            for (int j = 0; j < trainingSample.size(); j++) {
                Mushroom x_0 = trainingSample[j]
                s[i][j] = this.s(x, x_0, x.edible ? 0 : 1)
            }
        }

        double[][] f = new double[controlSample.size()][2]
        int[][] p_x = new int[controlSample.size()][2]
        int[][] pa_x = new int[controlSample.size()][2]
        for (int i = 0; i < controlSample.size(); i++) {
            f[i][0] = this.f(Arrays.copyOfRange(s[i], 0, X_0_edible_training.size()))
            f[i][1] = this.f(Arrays.copyOfRange(s[i], X_0_edible_training.size(), s[i].size()))

            Mushroom currentMushroom = controlSample[i]
            p_x[i][0] = currentMushroom.edible ? 1 : 0
            p_x[i][1] = currentMushroom.edible ? 0 : 1
            pa_x[i][0] = this.p_A(f[i], f[i][0])
            pa_x[i][1] = this.p_A(f[i], f[i][1])

            if (p_x[i][0] != pa_x[i][0]) {
                print "!!! "
            }
            String result = pa_x[i][0] == 1 ? "EDIBLE" : "POISONOUS"
            println("i = ${i}, mushroom id=${currentMushroom.id} result is " + result)
        }

        println()

        for (int i = 0; i < controlSample.size(); i++) {
            Mushroom currentMushroom = controlSample[i]
            String result = pa_x[i][0] == 1 ? "EDIBLE" : "POISONOUS"
            // println "Mushroom " + currentMushroom + " is " + result
        }
    }

    void calculationOfAlgorithmParameters(List<Mushroom> X_0_edible_training, List<Mushroom> X_0_poison_training) {
        int size = X_0_edible_training[0].attributeVector.size()

        double[][] b = new double[2][size]
        calculationOfAlgorithmParameters(b, 0, X_0_edible_training)
        calculationOfAlgorithmParameters(b, 1, X_0_poison_training)
        println()
        println "b : [\n\t${b[0]}, \n\t${b[1]}\n]"

        double[] b_ = new double[size]
        a = new double[2][size]

        for (int j = 0; j < size; j++) {
            b_[j] = 1 / 2 * (b[0][j] + b[1][j])
        }
        println()
        println "b_ : \n\t${b_}"

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < size; j++) {
                a[i][j] = Math.abs(b[i][j] - b_[j])
            }
        }
        println()
        println "a : [\n\t${a[0]}, \n\t${a[1]}\n]"
    }

    void calculationOfAlgorithmParameters(double[][] b, int i, List<Mushroom> currentClass) {
        for (int j = 0; j < b[i].length; j++) {
            int sum = currentClass
                    .stream()
                    .mapToInt({ x -> x[j] })
                    .sum()

            b[i][j] = 1 / currentClass.size() * sum
        }
    }

    double s(Mushroom x, Mushroom x_0, int i) {
        double sum1 = Arrays.stream(a[i]).sum()
        double sum2 = 0
        for (int j = 0; j < x.attributeVector.size(); j++) {
            int u = x[j] != x_0[j] ? -1 : 1
            sum2 += u * a[i][j]
        }

        return 1 / sum1 * sum2
    }

    double f(double[] s_i) {
        Arrays.stream(s_i).max().getAsDouble()
    }

    int p_A(double[] f, double f_i) {
        double max = Arrays.stream(f).max().getAsDouble()
        Math.abs(max - f_i) <= 0.001 ? 1 : 0
    }
}
