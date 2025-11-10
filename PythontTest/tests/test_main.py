import pytest
from src.main import schet
@pytest.mark.math
def test_sum():
    NUMBER1 = 15
    NUMBER2 = 7
    expected_result = 22
    actual_result = schet.sum(NUMBER1, NUMBER2)
    assert actual_result == expected_result
@pytest.mark.math
def test_minus():
    NUMBER1 = 16
    NUMBER2 = 7
    expected_result = 9
    actual_result = schet.minus(NUMBER1, NUMBER2)
    assert actual_result == expected_result
@pytest.mark.math
def test_umnozhenie():
    NUMBER1 = 10
    NUMBER2 = 3
    expected_result = 30
    actual_result = schet.umnozhenie(NUMBER1, NUMBER2)
    assert actual_result == expected_result
@pytest.mark.math
def test_delenie():
    NUMBER1 = 50
    NUMBER2 = 5
    expected_result = 10
    actual_result = schet.delenie(NUMBER1, NUMBER2)
    assert actual_result == expected_result
@pytest.mark.math
def test_sqrt():
    NUMBER1 = 25
    expected_result = 5
    actual_result = schet.sqrt(NUMBER1)
    assert actual_result == expected_result
@pytest.mark.exception
def test_delenieException():
    with pytest.raises(ValueError):
        schet.delenie(5, 0)
@pytest.mark.exception
def test_sqrtException():
    with pytest.raises(ValueError):
        schet.sqrt(-9)
@pytest.mark.math
@pytest.mark.data
@pytest.mark.parametrize("NUMBER1,NUMBER2,expected_result", [(6, 2, 4),(12, 3, 4),(42, 2, 21)])
def test_delenie2(NUMBER1, NUMBER2, expected_result):
    actual_result = schet.delenie(NUMBER1, NUMBER2)
    assert actual_result == expected_result