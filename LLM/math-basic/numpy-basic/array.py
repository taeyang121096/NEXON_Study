import numpy as np


# 각 잘형이 다를 때

p_list = [1, 2, 3, 4, 5, '6']
np_array = np.array(p_list)

print(p_list)
print(np_array)

# 2차원 이상 배열 구조일 때

p_list2 = [[1], [2,3], [4,5,6]]
np_array2 = np.array(p_list2, dtype=object)
np_array3 = np.array([[1],[2],[3]])

print(p_list2)
print(np_array2)
print(np_array3)

# 연산
p_list4 = [1, 2, 3, 4, 5]
p_list5 = [6, 7, 8, 9, 10]
np_array4 = np.array(p_list4)
np_array5 = np.array(p_list5)

print(p_list4 + p_list5)
# print(p_list4 * p_list5)
print(p_list4 * 3)

print(np_array4 + np_array5)
print(np_array4 * np_array5)
print(np_array4 * 3)

# 변경 가능 여부
p_list6 = [1, 2, 3, 4, 5]
np_array6 = np.array([[1,2], [3,4], [5,6]])
p_list6.append(7)
print(p_list6)
p_list6.pop()
print(p_list6)
print(np_array6.shape)

# numpy array는 shape를 변경할 수 없다.
# numpy 는 c로 구현되어 있어서 속도가 빠르다.
# numpy array는 같은 데이터 타입만 저장할 수 있다.


# 다 차원 배열

my_list = [
    [[1, 2, 3], [4, 5, 6], [7, 8, 9]],
    [[10, 11, 12], [13, 14, 15], [16, 17, 18]]
]
my_array = np.array(my_list)
print(my_list)
print(my_array)
print("차원 : ", my_array.ndim)
print("형태 : ", my_array.shape)
print("크기 : ", my_array.size)
print("타입 : ", my_array.dtype)
print("아이템 크기 : ", my_array.itemsize)

# 배열 생성

my_list1 = [1, 2, 3, 4, 5]
my_array1 = np.array(my_list1)
print(my_array1)

my_array2 = np.arange(0, 10, 2)
print(my_array2)

zero_array = np.zeros((3, 3))
print(zero_array)

one_array = np.ones((2, 4), dtype=int)
print(one_array)

empty_array = np.empty((3, 3))
print(empty_array)

full_array = np.full((2, 2), 7)
print(full_array)

eye_array = np.eye(3)
print(eye_array)

# 데이터 타입
# int, float, bool, str
# int8, int16, int32, int64

int_array = np.array([1, 2, 3, 4, 5], dtype=np.int32)
float_array = np.array([1, 2, 3, 4, 5], dtype=np.float64)
print("int_array의 원소 크기:", int_array.itemsize)
print("float_array의 원소 크기:", float_array.itemsize)

bool_array = np.array([True, False, True, False], dtype=np.bool)
str_array = np.array(['hellow', 'world', '안녕하세용 죽엉'], dtype=np.str_)

print("bool_array의 원소 크기:", bool_array.itemsize)
print("str_array의 원소 크기:", str_array.itemsize)