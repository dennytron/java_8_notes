void testStream() {
    List<Integer> l = Arrays.asList(1, 2, 3);
    l.add(1);
    l.add(2);
    l.add(3);

    List<Integer> l2 = l.stream()
            .filter(p -> p % 2 != 0)
            .sorted()
            .collect(toList());
}

void testStream2() {
    List<Integer> numbers1 = Arrays.asList(1, 2, 3);
    List<Integer> numbers2 = Arrays.asList(3, 4);
    String s = "1,2,3,4,5";
    
    Arrays.asList(s.split(","));

    List<String> pairs = numbers1.stream()
            .flatMap(i -> numbers2.stream()
            .map(j -> String.format("%d %d", i, j)))
            .collect(toList());

    pairs.forEach(System.out::println);

    numbers1.stream()
            .map(i -> i*i)
            .forEach(System.out::println);
}
