import pytest
from src.main import schet
@pytest.mark.math
def test_umnozhenie():
    NUMBER1 = 10
    NUMBER2 = 3
    expected_result = 30
    actual_result = schet.umnozhenie(NUMBER1, NUMBER2)
    assert actual_result == expected_result
@pytest.mark.math
def test_stepen():
    NUMBER1 = 5
    NUMBER2 = 3
    expected_result = 125
    actual_result = schet.stepen(NUMBER1, NUMBER2)
    assert actual_result == expected_result
@pytest.mark.math
def test_procent():
    NUMBER1 = 200
    NUMBER2 = 40
    expected_result = 80
    actual_result = schet.procent(NUMBER1, NUMBER2)
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
        schet.sqrt(-625)
@pytest.mark.math
@pytest.mark.data
@pytest.mark.parametrize("NUMBER1,NUMBER2,expected_result", [(6, 2, 36),(12, 2, 144),(2, 5, 32)])
def test_delenie2(NUMBER1, NUMBER2, expected_result):
    actual_result = schet.stepen(NUMBER1, NUMBER2)
    assert actual_result == expected_result