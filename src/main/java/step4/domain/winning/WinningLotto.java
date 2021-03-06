package step4.domain.winning;

import step4.exception.LottoNullPointerException;
import step4.domain.lotto.Lotto;
import step4.domain.lotto.LottoNumber;
import step4.exception.LottoNumberConflictException;
import step4.exception.LottoNumberNullPointerException;

import java.util.Objects;

public final class WinningLotto {

    private final Lotto winningLotto;
    private final LottoNumber bonusLottoNumber;

    private WinningLotto(Lotto winningLotto, LottoNumber bonusLottoNumber) {
        validateLottoNull(winningLotto);
        validateLottoNumberNull(bonusLottoNumber);
        validateLottoNumberConflict(winningLotto, bonusLottoNumber);
        this.winningLotto = winningLotto;
        this.bonusLottoNumber = bonusLottoNumber;
    }

    public static final WinningLotto from(String winningLotto, int bonusLottoNumber) {
        return from(Lotto.of(winningLotto), LottoNumber.valueOf(bonusLottoNumber));
    }

    public static final WinningLotto from(Lotto winningLotto, LottoNumber bonusLottoNumber) {
        return new WinningLotto(winningLotto, bonusLottoNumber);
    }

    private final void validateLottoNull(Lotto winningLotto) {
        if (Objects.isNull(winningLotto)) {
            throw new LottoNullPointerException();
        }
    }

    private final void validateLottoNumberNull(LottoNumber bonusLottoNumber) {
        if (Objects.isNull(bonusLottoNumber)) {
            throw new LottoNumberNullPointerException();
        }
    }

    private final void validateLottoNumberConflict(Lotto winningLotto, LottoNumber bonusLottoNumber) {
        if (winningLotto.contains(bonusLottoNumber)) {
            throw new LottoNumberConflictException();
        }
    }

    public final WinningStatus getWinningStatus(Lotto lotto) {
        return WinningStatus.from(getCountOfMatch(lotto), isIncludeLottoNumber(lotto));
    }

    private final long getCountOfMatch(Lotto lotto) {
        return winningLotto.getCountMatch(lotto);
    }

    private final boolean isIncludeLottoNumber(Lotto lotto) {
        return lotto.contains(bonusLottoNumber);
    }

}
