package lotto.exceptioin;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InputException {

    private final Scanner scanner = new Scanner(System.in);

    // 로또 구매 금액을 입력받아 검증하는 메서드
    public int getValidMoney() {
        while (true) {
            try {
                System.out.println("구입금액을 입력해 주세요.");
                int money = Integer.parseInt(scanner.nextLine().trim());
                validateMoney(money);
                return money;
            } catch (IllegalArgumentException e) {
                System.out.println("[ERROR] " + e.getMessage());
            }
        }
    }

    // 로또 당첨 번호를 입력받아 검증하는 메서드
    public List<Integer> getValidWinningNumbers() {
        while (true) {
            try {
                System.out.println("당첨 번호를 입력해 주세요.");
                String input = scanner.nextLine().trim();
                List<Integer> winningNumbers = Stream.of(input.split(","))
                        .map(String::trim)
                        .map(Integer::parseInt)
                        .collect(Collectors.toList());
                validateLottoNumbers(winningNumbers);
                return winningNumbers;
            } catch (IllegalArgumentException e) {
                System.out.println("[ERROR] " + e.getMessage());
            }
        }
    }

    // 보너스 번호를 입력받아 검증하는 메서드
    public int getValidBonusNumber() {
        while (true) {
            try {
                System.out.println("보너스 번호를 입력해 주세요.");
                int bonusNumber = Integer.parseInt(scanner.nextLine().trim());
                validateBonusNumber(bonusNumber);
                return bonusNumber;
            } catch (IllegalArgumentException e) {
                System.out.println("[ERROR] " + e.getMessage());
            }
        }
    }

    // 로또 번호 개수 및 범위 검증
    public void validateLottoNumbers(List<Integer> numbers) {
        if (numbers == null || numbers.size() != 6) {
            throw new IllegalArgumentException("로또 번호는 6개의 숫자로 입력되어야 합니다.");
        }
        for (Integer number : numbers) {
            validateLottoNumberRange(number);
        }
    }

    // 각 로또 번호 범위 검증
    private void validateLottoNumberRange(int number) {
        if (number < 1 || number > 45) {
            throw new IllegalArgumentException("로또 번호는 1부터 45 사이의 숫자여야 합니다.");
        }
    }

    // 보너스 번호 범위 검증
    public void validateBonusNumber(int bonusNumber) {
        if (bonusNumber < 1 || bonusNumber > 45) {
            throw new IllegalArgumentException("보너스 번호는 1부터 45 사이의 숫자여야 합니다.");
        }
    }

    // 구매 금액 유효성 검증
    public void validateMoney(int money) {
        if (money <= 0 || money % 1000 != 0) {
            throw new IllegalArgumentException("구매 금액은 1,000원 단위의 양수여야 합니다.");
        }
    }
}
